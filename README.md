This is a repo for my data mining class ITCS 3162 at UNC Charlotte.

In this project we are given a large list of URLs for players of the International Tennis Foundation (Juniors),
and we will need to scrape the data off these links for use in the data mining portion of this project.

===Note: some of the code made be replicated, and as such credit is due to the author of the video. This video is used for reference and guiding material in how to build a basic web scraper.
===The scraper is an essential part of our project, however it is far from the only part and it is up to us on how we choose to extract the data from the pages.
===The file containing all the links, gathered by our classmates as a previous assignment, has also been provided in this repository.

Steps for group members (Using Windows):

I used this video for reference: https://www.youtube.com/watch?v=XQgXKtPSzUI

Install Anaconda: https://www.continuum.io/downloads

Have a text editor of some sort (I prefer Atom, Sublime Text is fine as well as ViM)

Run this line in Anaconda CLI to get BeautifulSoup set up:

pip install bs4

Create a new .py file (I call my crawl.py) and include these two lines as imports:

from urllib.request import urlopen as uReq

from bs4 import BeautifulSoup as soup
