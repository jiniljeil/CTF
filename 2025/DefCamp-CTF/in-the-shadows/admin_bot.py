#!/usr/bin/env python3

import time
import sys
import requests
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
import urllib.parse
import threading


class AdminBot:
    def __init__(self, base_url="http://localhost:5001"):
        self.base_url = base_url
        self.driver = None
        self.setup_driver()

    def setup_driver(self):
        chrome_options = Options()
        chrome_options.add_argument("--headless")
        chrome_options.add_argument("--no-sandbox")
        chrome_options.add_argument("--disable-dev-shm-usage")
        chrome_options.add_argument("--disable-gpu")
        chrome_options.add_argument("--window-size=1920,1080")
        chrome_options.add_argument("--user-data-dir=/tmp/chrome-profile")
        chrome_options.add_argument("--data-path=/tmp/chrome-data")
        chrome_options.add_argument("--disk-cache-dir=/tmp/chrome-cache")

        try:
            self.driver = webdriver.Chrome(options=chrome_options)
            self.driver.set_page_load_timeout(5)
        except Exception as e:
            print(f"Error setting up Chrome driver: {e}")
            print("Make sure ChromeDriver is installed and in PATH")
            sys.exit(1)

    def set_flag_cookie(self, flag):
        try:
            self.driver.get("about:blank")

            self.driver.execute_cdp_cmd(
                "Network.setCookie",
                {
                    "name": "flag",
                    "value": flag,
                    "domain": "localhost",
                    "path": "/",
                    "httpOnly": True,
                    "secure": False,
                    "sameSite": "Lax",
                },
            )

            return True

        except Exception as e:
            return False

    def visit_code_url(self, code):
        try:
            code_url = f"{self.base_url}/?code={urllib.parse.quote(code)}"

            self.driver.get(code_url)

            WebDriverWait(self.driver, 5).until(
                EC.presence_of_element_located((By.TAG_NAME, "body"))
            )

            time.sleep(5)

            try:
                alert = self.driver.switch_to.alert
                alert.accept()
            except:
                pass

            return True

        except Exception as e:
            return False

    def simulate_admin_flow(self, flag, code):
        if not self.set_flag_cookie(flag):
            return False

        if not self.visit_code_url(code):
            return False

        return True

    def cleanup(self):
        if self.driver:
            self.driver.quit()


def main():
    if len(sys.argv) != 3:
        print("Usage: python admin_bot.py <flag> <code>")
        print("Example: python admin_bot.py 'DCTF{test_flag}' 'alert(\"Hello World\")'")
        sys.exit(1)

    flag = sys.argv[1]
    code = sys.argv[2]

    bot = AdminBot()

    try:
        success = bot.simulate_admin_flow(flag, code)
        if success:
            print("\n🎉 Admin bot completed successfully!")
        else:
            print("\n❌ Admin bot encountered errors")
            sys.exit(1)

    except KeyboardInterrupt:
        print("\n⚠️  Admin bot interrupted by user")

    except Exception as e:
        print(f"\n❌ Unexpected error: {e}")
        sys.exit(1)

    finally:
        bot.cleanup()


if __name__ == "__main__":
    main()
