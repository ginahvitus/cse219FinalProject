// CONSTANTS
var IMG_PATH;
var ICON_PATH;
var IMG_WIDTH;
var SCALED_IMAGE_HEIGHT;
var FADE_TIME;
var SLIDESHOW_SLEEP_TIME;

// DATA FOR CURRENT SLIDE
var title;
var slides;
var currentSlide;

// TIMER FOR PLAYING SLIDESHOW
var timer;

function Slide(initImgFile, initCaption) {
    this.imgFile = initImgFile;
    this.caption = initCaption;
}

function initSlideshow() {
    IMG_PATH = "./img/";
    ICON_PATH = "./icons/";
    IMG_WIDTH = 1000;
    SCALED_IMAGE_HEIGHT = 500.0;
    FADE_TIME = 1000;
    SLIDESHOW_SLEEP_TIME = 3000;
    slides = new Array();
    var slideshowDataFile = "./data/SlideshowData.json";
    loadData(slideshowDataFile);
    timer = null;
}

function initPage() {
    $("#slideshow_title").html(title);
    if (currentSlide >= 0) {
	$("#slide_caption").html(slides[currentSlide].caption);
	$("#slide_img").attr("src", IMG_PATH + slides[currentSlide].imgFile);
	$("#slide_img").one("load", function() {
	    autoScaleImage();
	});
    }
}

function autoScaleImage() {
	var origHeight = $("#slide_img").height();
	var scaleFactor = SCALED_IMAGE_HEIGHT/origHeight;
	var origWidth = $("#slide_img").width();
	var scaledWidth = origWidth * scaleFactor;
	$("#slide_img").height(SCALED_IMAGE_HEIGHT);
	$("#slide_img").width(scaledWidth);
	var left = (IMG_WIDTH-scaledWidth)/2;
	$("#slide_img").css("left", left);
}

function fadeInCurrentSlide() {
    var filePath = IMG_PATH + slides[currentSlide].imgFile;
    $("#slide_img").fadeOut(FADE_TIME, function(){
	$(this).attr("src", filePath).bind('onreadystatechange load', function(){
	    if (this.complete) {
		$(this).fadeIn(FADE_TIME);
		$("#slide_caption").html(slides[currentSlide].caption);
		autoScaleImage();
	    }
	});
    });     
}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function(json) {
	loadSlideshow(json);
	initPage();
    });
}

function loadSlideshow(slideshowData) {
    title = slideshowData.title;
    for (var i = 0; i < slideshowData.slides.length; i++) {
	var rawSlide = slideshowData.slides[i];
	var slide = new Slide(rawSlide.image_file_name, rawSlide.caption);
	slides[i] = slide;
    }
    if (slides.length > 0)
	currentSlide = 0;
    else
	currentSlide = -1;
}

function processPreviousRequest() {
    currentSlide--;
    if (currentSlide < 0)
	currentSlide = slides.length-1;
    fadeInCurrentSlide();
}

function processPlayPauseRequest() {
    if (timer === null) {
	timer = setInterval(processNextRequest, SLIDESHOW_SLEEP_TIME);
	$("#play_pause_button").attr("src", ICON_PATH + "Pause.png");
    }
    else {
	clearInterval(timer);
	timer = null;
	$("#play_pause_button").attr("src", ICON_PATH + "Play.png");
    }	
}

function processNextRequest() {
    currentSlide++;
    if (currentSlide >= slides.length)
	currentSlide = 0;
    fadeInCurrentSlide();
}