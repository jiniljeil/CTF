#!/usr/bin/env python3
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
import time, sys, os, re
from pyvirtualdisplay import Display

# receive url from stdin (ynetd)
writeup_id = input().strip()
if not re.match('^[0-9a-f]{16}$', writeup_id):
    sys.exit(-1)

print('visting - ' + writeup_id, file=sys.stderr, flush=True)

display = Display(visible=0, size=(800, 600))
display.start()
chrome_options = Options()
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument('--headless')
chrome_options.add_argument('--no-sandbox')
driver = webdriver.Chrome('/usr/bin/chromedriver', options=chrome_options)

url = 'http://admin:__ADMIN_TOKEN__@127.0.0.1/login_admin.php?id='+writeup_id
driver.get(url)
element = driver.find_element_by_xpath('//input[@id="like"]')
element.click()

driver.quit()
display.stop()

print('done - ' + writeup_id,  file=sys.stderr, flush=True)
