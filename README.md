<h1>This is a repo for my data mining class ITCS 3162 at UNC Charlotte.</h1>

<h2><strong>Group members:</strong></h2> Thomas Nix, Jesus Garcia, and Seth Morris

In this project we are given a large list of URLs for players of the International Tennis Foundation (Juniors),
and we will need to scrape the data off these links for use in the data mining portion of this project.

<em>===Note: some of the code made be replicated, and as such credit is due to the author of the video. This video is used for reference and guiding material in how to build a basic web scraper. <br/>
===The scraper is an essential part of our project, however it is far from the only part and it is up to us on how we choose to extract the data from the pages. <br/>
===The file containing all the links, gathered by our classmates as a previous assignment, has also been provided in this repository.</em> <br/>

<h3>Steps for group members (Using Windows):</h3>
<ul>

<li>I used this video for reference: https://www.youtube.com/watch?v=XQgXKtPSzUI </li>

<li>Install Anaconda: https://www.continuum.io/downloads </li>

<li>Have a text editor of some sort (I prefer Atom, Sublime Text is fine as well as ViM) </li>

<li>Run this line in Anaconda CLI to get BeautifulSoup set up: pip install bs4 </li>

<li> Create a new .py file (I call my crawl.py) and include these two lines as imports: <br/> </li>

<br/> from urllib.request import urlopen as uReq <br/>

from bs4 import BeautifulSoup as soup

</ul>
