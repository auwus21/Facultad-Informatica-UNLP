import sys
import pypdf

reader = pypdf.PdfReader(sys.argv[1])
text = ""
for page in reader.pages:
    extracted = page.extract_text()
    if extracted:
        text += extracted + "\n"

with open(sys.argv[2], "w") as f:
    f.write(text)
