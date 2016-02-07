# Project 1 - *Near Me*

**Near Me** is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: **40** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current popular photos** from Instagram
* [x] For each photo displayed, user can see the following details:
  * [x] Graphic, Caption, Username
  * [x] Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [x] User can **pull-to-refresh** popular stream to get the latest popular photos
* [x] Show latest comments for each photo
* [x] Display each photo with the same style and proportions as the real Instagram
* [x] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [x] Display a nice default placeholder graphic for each image during loading
* [x] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [x] Show last 2 comments for each photo
* [x] Allow user to view all comments for an image within a separate activity or dialog fragment
* [x] Allow video posts to be played in full-screen using the VideoView


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Challenges and something to explore:

1) How to break xml into multiple xml and how to import the same resource in different xml
2) How to shadow the image behind the image view so that there are no white backdround
3) Circle is not clear in the comments
4) HTML.formathtml() not working and then start working suddenly.
5) Having problem with showing comments list to dialog fragment and then did the right pattern approach

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
-[RoundImage](com.makeramen:roundedimageview:2.2.1) - Round image view
-[Butterknife](com.jakewharton:butterknife:7.0.1) - Annotation based programming

## License

    Copyright [2016] [Anuj Acharya]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
