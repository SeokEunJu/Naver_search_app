# [Mobile App Programing] Personal Project
Get input from the user, then show result in news and blog categories.

## 1. Project Overview

### What API is Used?

When I needed to create an application that utilizes the API, the first thing that came up with is the ["Naver API"](https://developers.naver.com/main/).
Because I use Naver almost every day, I have an affinity for Naver itself.
While searching for Naver APIs, the search API was the most useful, so I chose to develop an app with search API in Naver.

## 2. Application Overview

### First Screen

<p align="center">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85147314-a4995f80-b289-11ea-8df6-311dca97cb97.jpg">
</p>


This is first screen of my application.
A first edittext window is for getting input from user.
A first button, bottom of edittext window is for showing result of news category.
A second button, bottom of edittext window is for showing result of blog category.

### When click button

<div style="width:50; margin:0 auto;">
<p align="center">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85148217-d828b980-b28a-11ea-997d-8d8c3e03c594.jpg">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85148384-0a3a1b80-b28b-11ea-95f6-09c11356b425.jpg">
</p>
</div>

Left image shows results of news and right image shows results of blog.

### Error Handling - No input

<p align="center">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85148822-8af91780-b28b-11ea-9410-c949f9545aea.png">
</p>

When user clicked the button without putting any input in edittext window, the app show toast message that alert to put any input in application.

### When click result object

<div style="width:50; margin:0 auto;">
<p align="center">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85149240-04910580-b28c-11ea-9fcd-f2210d09fa5d.png">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85149432-3f933900-b28c-11ea-896f-35f2f5022988.png">
</p>
</div>

When User clicked the result object in left image, the app redirect to page like right image.

## 3. My Backend Server - Firebase

### Pay attention to the banner images

<p align="center">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85149757-a3b5fd00-b28c-11ea-8313-236a18a7b450.png">
<img width="300" src="https://user-images.githubusercontent.com/50102137/85149781-ac0e3800-b28c-11ea-9b52-6744d378b6d4.png">
</p>

Do you remember this banner images?
