window.isVisibleInViewport = function (elem) {
    if (!elem || !(elem instanceof Element)) {
        return false;
    }
    let vw = window.innerWidth;
    let vh = window.innerHeight;
    let ratio = 0;
    let observer = new IntersectionObserver((entries) => {
        let entry = entries[0];
        ratio = entry.intersectionRatio;
        });
    observer.observe(elem);
    observer.disconnect();
    return ratio > 0;
};