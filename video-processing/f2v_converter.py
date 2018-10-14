"""
This file is used for converting frames to video
Usage: python f2v_converter.py [frames_directory] [video-destination-dir] [video-name] [fps]
"""

import cv2
import numpy as np
import os
import sys
from utils import convert_frames_to_video
 
from os.path import isfile, join

def main():
    if len(sys.argv) != 5:
        print('Usage: python f2v_converter.py [frames_directory] [video-destination-dir] [video-name] [fps]')
        return
    pathIn = sys.argv[1]
    pathOut = sys.argv[2]
    if not os.path.exists(pathIn):
        print('Input Dir not exists')
        return
    if not os.path.exists(pathOut):
        os.makedirs(pathOut)
        print('Output Dir created')
    pathOut = os.path.join(pathOut, sys.argv[3])
    fps = int(sys.argv[4])
    convert_frames_to_video(pathIn, pathOut, fps)
 
if __name__=="__main__":
    main()