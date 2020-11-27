# coding=UTF-8
import json
import os
import re
from asyncio import subprocess

import ffmpeg
import requests
import regex
from bs4 import BeautifulSoup

requests.packages.urllib3.disable_warnings()

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36',
    'Referer': 'https://www.bilibili.com/'
}


def GetBiliVideo(homeurl, num, session=requests.session()):
    response = session.get(url=homeurl, headers=headers, verify=False)
    print(response.text)
    data_re = re.compile('(?<=window.__playinfo__=).*(?=</script><script>window.__B)')
    #title_re = re.compile('(?<=<h1 title=").*(?=</h1>)')
    dir_re = re.compile('(?<=name": ").*(?=",)')
    response_html = response.text
    match = data_re.search(response_html)
    title =BeautifulSoup(response_html).find('h1').text
    if type(match) is not None:
        videoJson = json.loads(match.group(0))
        VideoURL = videoJson['data']['dash']['video'][0]['base_url']
        AudioURl = videoJson['data']['dash']['audio'][0]['baseUrl']
    # title_match = title_re.search(response_html)
    # if type(title_match) is not None:
    #     title = title_match.group(0)
    dir = dir_re.search(response_html).group(0)
    if not os.path.exists(dir):
        # 如果不存在则创建目录
        # 创建目录操作函数
        os.makedirs(dir)
        print('目录文件创建成功!')
    # 获取每一集的名称
    # name = listjson['videoData']['pages'][num]['part']
    print(title)
    # 下载视频和音频
    print('正在下载 "' + title + '" 的视频····')
    BiliBiliDownload(homeurl=homeurl, url=VideoURL, name=os.getcwd() + '/' + dir + '/' + title + '_Video.mp4',
                     session=session)

    print('正在下载 "' + title + '" 的音频····')
    BiliBiliDownload(homeurl=homeurl, url=AudioURl, name=os.getcwd() + '/' + dir + '/' + title + '_Audio.mp3',
                     session=session)
    print('正在组合 "' + title + '" 的视频和音频····')
    CombineVideoAudio(title + '_Video.mp4', title + '_Audio.mp3', title + '_output.mp4')
    print(' "' + title + '" 下载完成！')


def combine_audio(video_file, audiio_file, out_file):
    try:
        cmd = '/usr/local/bin/ffmpeg -i ' + video_file + ' -i ' + audiio_file + ' -acodec copy ' + out_file
        print(cmd)
        subprocess.call(cmd, shell=True)  # "Muxing Done
        print('Muxing Done')
        return True
    except Exception:
        return False


def BiliBiliDownload(homeurl, url, name, session=requests.session()):
    headers.update({'Referer': homeurl})
    session.options(url=url, headers=headers, verify=False)
    # 每次下载1M的数据
    begin = 0
    end = 1024 * 512 - 1
    flag = 0
    while True:
        headers.update({'Range': 'bytes=' + str(begin) + '-' + str(end)})
        res = session.get(url=url, headers=headers, verify=False)
        if res.status_code != 416:
            begin = end + 1
            end = end + 1024 * 512
        else:
            headers.update({'Range': str(end + 1) + '-'})
            res = session.get(url=url, headers=headers, verify=False)
            flag = 1
        with open(name, 'ab') as fp:
            fp.write(res.content)
            fp.flush()

        # data=data+res.content
        if flag == 1:
            fp.close()
            break


def CombineVideoAudio(videopath, audiopath, outpath):
    combine_audio(videopath, audiopath, outpath)
    os.remove(videopath)
    os.remove(audiopath)


if __name__ == '__main__':

    # av44518113
    # av = input('请输入视频号：')
    url = 'https://www.bilibili.com/bangumi/play/ep251326'
    GetBiliVideo(homeurl=url, num=1)
    # 视频选集
    range_start = input('从第几集开始？')
    range_end = input('到第几集结束？')
    if int(range_start) <= int(range_end):
        for i in range(int(range_start), int(range_end) + 1):
            pass

    else:
        print('选集不合法！')
