args = arguments[0];

function Counter() {
	this.map = {};
	this.increase = function(element) {
		if (element.tagName in this.map) {
			this.map[element.tagName] = this.map[element.tagName] + 1;
		} else {
			this.map[element.tagName] = 1;
		}
		return this.map[element.tagName];
	};
}

function getText(node) {
	// 3 is text node
	if (node.childNodes[0] && node.childNodes[0].nodeType == 3) {
		return node.childNodes[0].nodeValue;
	}
	return "";
}

function getX(node) {
	var rect = node.getBoundingClientRect();
	return rect.left + window.scrollX;
}

function getY(node) {
	var rect = node.getBoundingClientRect();
	return rect.top + window.scrollY;
}

function transform(node) {
	var result = {
		"tagName" : node.tagName,
		"text" : getText(node),
		"shown" : isShown(node)
	};
	var attrs = node.attributes;
	for (var i = 0; i < attrs.length; i++) {
		result[attrs[i].name] = attrs[i].value;
	}
	var style = window.getComputedStyle(node);
	for (var i = 0; i < args.length; i++) {
		result[args[i]] = style[args[i]];
	}
	// They need special treatment
	result["x"] = getX(node);
	result["y"] = getY(node);
	result["width"] = node.getBoundingClientRect().width;
	result["height"] = node.getBoundingClientRect().height;
	return result;
}

function isShown(e) {
	return !!(e.offsetWidth || e.offsetHeight || e.getClientRects().length);
}

function mapElement(element, parentPath, allElements) {
	if (!element || !element.children) {
		return allElements;
	}
	var counter = new Counter();
	for (var i = 0; i < element.children.length; i++) {
		var child = element.children[i];
		var cnt = counter.increase(child);
		var path = parentPath + "/" + child.tagName + "[" + cnt + "]";
		allElements[path] = transform(child);
		mapElement(child, path, allElements);
	}
	return allElements;
}

var htmlNode = document.getElementsByTagName("html")[0];
var html = transform(htmlNode);
var result = mapElement(htmlNode, "//HTML[1]", {
	"//HTML[1]" : html
});
return result;
