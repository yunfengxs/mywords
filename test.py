#!/usr/bin/env python
# -*- coding:utf-8 -*-      
#Author: nulige
 
import socket
from bs4 import BeautifulSoup
from urllib.request import urlopen
from urllib.request import Request
import random
import re


def gethtml(word):
    def getContent(url,headers):
        random_header = random.choice(headers)
        header = {"User-Agent": random_header, "GET": url, "Host": "sp0.baidu.com", "Referer": "https://www.baidu.com"}
        req=Request(url,None,header)
        content=urlopen(req).read().decode("utf-8")
        return content
    mywords=word
    sss="https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_4_dg&wd="+mywords
    url=sss
    
    my_headers = ["Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0"]
    bs=BeautifulSoup(getContent(url,my_headers),"html.parser")
    bs2=(bs.find_all('div',mu="http://fanyi.baidu.com/#en/zh/"+mywords))
    bs3=bs.find_all('a', class_="op_dict3_how_read")
    for i in bs3:
        idl = i.get('url')
    strs=""
    for span in bs2:
        strs=span.get_text()
    if(strs==""):
        return "no"
    strs=strs[18:]
    strs=strs.replace("\n", "")
    strs=strs.replace(" ", "")

    result = re.findall(".*牛津词典",strs)
    
    for x in result:
        if x is None:
            return "no"
        else:          
            x=x[:-4]
            return x+"&&"+idl

                             
phone=socket.socket(socket.AF_INET,socket.SOCK_STREAM) 
phone.bind(('0.0.0.0',3000))  
phone.listen(5) 
print('------------->')
while True:
    conn,addr=phone.accept()  #
    msg=conn.recv(1024)  #
    print('msg：',msg)
    fuck=msg.decode()
    type(fuck)
    sssss=gethtml(fuck)+"\n"
    conn.send(sssss.encode(encoding="utf-8"))

#conn.close()
#phone.close()
