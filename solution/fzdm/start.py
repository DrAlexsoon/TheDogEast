import os
import re
from contextlib import closing
from tqdm import tqdm

from bs4 import BeautifulSoup
from pip._vendor import requests

domains = ["http://www-mipengine-org.mipcdn.com/i/p3.manhuapan.com", "http://p1.manhuapan.com",
           "http://p5.manhuapan.com", "http://p17.manhuapan.com"
    , "http://p6.manhuapan.com"]
oldDomain = "https://p5.manhuapan.com"
# url = "https://manhua.fzdm.com/2/996/"
# r = requests.get(url=url)

save_dir = '海贼王'
if save_dir not in os.listdir('/'):
    os.mkdir(save_dir)

catalog = "https://manhua.fzdm.com/2/"
r = requests.get(catalog)
html = BeautifulSoup(r.text, 'html.parser')
cartoon_list = html.select('li.pure-u-1-2.pure-u-lg-1-4 a')
# 获取动漫章节链接和章节名
chapter_names = []
chapter_urls = []
for cartoon in cartoon_list:
    href = catalog + cartoon.get('href')
    name = cartoon.text
    chapter_names.insert(0, name)
    chapter_urls.insert(0, href)

for i, url in enumerate(tqdm(chapter_urls)):
    download_header = {
        'Referer': url
    }
    name = chapter_names[i]
    while '.' in name:
        name = name.replace('.', '')
    chapter_save_dir = os.path.join(save_dir, name)
    if name not in os.listdir(save_dir):
        os.mkdir(chapter_save_dir)
    for i, index in enumerate(range(0, 500)):
        if i != 0:
            currentUrl = url + "index_" + str(i) + ".html"
        else:
            currentUrl = url
        r = requests.get(url=currentUrl)
        if r.status_code == 404:
            break
        html = BeautifulSoup(r.text, 'html.parser')
        html = str(html)
        imagesPics = re.findall('(?<= mhurl=\").*?(?=\")', str(html))

        if len(imagesPics) < 0:
            print("pic uri not fund")
        else:
            imagesPic = imagesPics[0]
        page = imagesPic[imagesPic.rfind('/') + 1:]
        pic_save_path = os.path.join(chapter_save_dir, page)
        for i, domain in enumerate(domains):
            if "2016" or "2017" or "2018" or "2019" or "2020" not in imagesPic:
                domain = oldDomain

            imageUrl = domain + "/" + imagesPic
            with closing(requests.get(imageUrl, headers=download_header, stream=True)) as response:
                chunk_size = 1024
                content_size = int(response.headers['content-length'])
                if response.status_code == 200:
                    with open(pic_save_path, "wb") as file:
                        for data in response.iter_content(chunk_size=chunk_size):
                            file.write(data)
                    break
                else:
                    print('链接异常')
# getCookie("picHost").replace("/%3A/", ":").replace("%3A", ":");
