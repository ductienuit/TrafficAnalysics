import cv2
import numpy as np
import os
import sys
from utils import convert_frames_to_video
 
from os.path import isfile, join

def main():
    if len(sys.argv) != 4:
        return
    pathIn = sys.argv[1]
    pathOut = sys.argv[2]
    if not os.path.exists(pathIn):
        print('Input Dir not exists')
        return
    if not os.path.exists(pathOut):
        os.makedirs(pathOut)
        print('Output Dir created')
    pathOut = os.path.join(pathOut, 'res.avi')
    fps = int(sys.argv[3])
    convert_frames_to_video(pathIn, pathOut, fps)
 
if __name__=="__main__":
    main()