import os
import re
import json
import requests
from tqdm import tqdm
from bs4 import BeautifulSoup

path = '/'

headers = {
    'referer': 'https://www.acfun.cn/',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83'
}

class m3u8_url():
    def __init__(self, f_url):
        self.url = f_url

    def get_m3u8(self):
        global flag, qua, rel_path
        html = requests.get(self.url, headers=headers).text
        first_json = json.loads(re.findall('window.pageInfo = window.bangumiData = (.*?)};', html)[0] + '}', strict=False)
        name = first_json['title'].strip().replace("|",'')
        video_info = json.loads(first_json['currentVideoInfo']['ksPlayJson'], strict=False)['adaptationSet'][0]['representation']
        Label = {}
        num = 0
        for quality in video_info:  # 清晰度
            num += 1
            Label[num] = quality['qualityLabel']
        print(Label)
        choice = int(input("请选择清晰度: "))
        Download(name + '[{}]'.format(Label[choice]), video_info[choice - 1]['url'], path).start_download()

class Download():
    urls = []

    def __init__(self, name, m3u8_url, path):
        '''
        :param name: 视频名
        :param m3u8_url: 视频的 m3u8文件 地址
        :param path: 下载地址
        '''
        self.video_name = name
        self.path = path
        self.f_url = str(m3u8_url).split('hls/')[0] + 'hls/'
        with open(self.path + '/{}.m3u8'.format(self.video_name), 'wb')as f:
            f.write(requests.get(m3u8_url, headers={'user-agent': 'Chrome/84.0.4147.135'}).content)

    def get_ts_urls(self):
        with open(self.path + '/{}.m3u8'.format(self.video_name), "r") as file:
            lines = file.readlines()
            for line in lines:
                if '.ts' in line:
                    self.urls.append(self.f_url + line.replace('\n', ''))

    def start_download(self):
        self.get_ts_urls()
        for url in tqdm(self.urls, desc="正在下载 {} ".format(self.video_name)):
            movie = requests.get(url, headers={'user-agent': 'Chrome/84.0.4147.135'})
            with open(self.path + '/{}.flv'.format(self.video_name), 'ab')as f:
                f.write(movie.content)
        os.remove(self.path + '/{}.m3u8'.format(self.video_name))

url1 = input("输入地址: ")
m3u8_url(url1).get_m3u8()