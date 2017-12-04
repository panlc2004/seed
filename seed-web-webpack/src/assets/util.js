String.prototype.startWith = function (str) {
    var reg = new RegExp('^' + str);
    return reg.test(this);
}

String.prototype.endWith = function (str) {
    var reg = new RegExp(str + "$");
    return reg.test(this);
}

String.prototype.replaceAll = function (str, value) {
    var regExp = new RegExp(str, 'gm');
    return this.replace(regExp, value);
};