alice
=====

A demonstration of how to replace gwtp-crawler with gwt-pushstate

This project uses a simple pattern that will speed up your gwtp app, improve SEO and make it more accessable.

Live site: http://4.pushstateslash.appspot.com

This is a website that displays chapters of alice in wonderland.

It has only 1 RouteTokenPlace: /alice/{chapterNumber}

So here's what happens when you visit /alice/3

###### On the server:

* The server translates the place request and sees that the user wants chapter 3.

* It then calls the FetchChapterHandler and stores the result in a javascript map `var PREFETCH_DISPATCH` (Check the pages html source to see this.)

* Then it takes the result of FetchChapterHandler and uses it to create a very simple html version of the chapter which it includes in the html source noscript tag.

###### On the client:

* The HomePagePresenter is called as normal and also sees that the user wants chapter 3.

* It asks the dispatcher for chapter 3 and the dispatcher seeing that the result is in the javascript PREFETCH_DISPATCH map returns it immediately.

* The HomePage presenter displays the chapter as well as a next and previous button.

* If you click next or previous the page won't reload but instead the new chapter will be fetched from the server and displayed.

# The Advantages

* This pattern makes your site display faster because the dispatcher doesn't have to contact the server for the first call.

* Your page has content for non-javascript agents, so you've improved SEO and accessability.

* Plus because the noScript content is very similar to the PREFETCH_DISPATCH content gzip will make the noScript content virtually costless.

# The Disadvantage

There are no disadvantages, this pattern is amazing.

