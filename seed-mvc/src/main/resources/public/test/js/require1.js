define(function (require) {
    function test() {
        require(['math'], function (math) {
            alert(math.add(1, 1));
        });
    }
    test();
})

