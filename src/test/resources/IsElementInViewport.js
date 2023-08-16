window.isVisibleInViewport = function () {
    elem = $x("//img[@alt='Sytner Group Logo']/ancestor::div[contains(@class, 'Navbar_sui-navbar-fixed')]")
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
    console.log("ratio: " + ratio);
    return ratio > 0;
};