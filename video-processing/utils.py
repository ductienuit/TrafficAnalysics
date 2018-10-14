"""
Helpful functions
"""

import cv2
import logging
import logging.handlers
import math
import sys
import numpy as np
import os
from os.path import isfile, join

def save_frame(frame, file_name, flip=True):
    # flip BGR to RGB
    if flip:
        cv2.imwrite(file_name, np.flip(frame, 2))
    else:
        cv2.imwrite(file_name, frame)


def init_logging(to_file=False):
    main_logger = logging.getLogger()

    formatter = logging.Formatter(
        fmt='%(asctime)s.%(msecs)03d %(levelname)-8s [%(name)s] %(message)s', datefmt='%Y-%m-%d %H:%M:%S')

    handler_stream = logging.StreamHandler(sys.stdout)
    handler_stream.setFormatter(formatter)
    main_logger.addHandler(handler_stream)

    if to_file:
        handler_file = logging.handlers.RotatingFileHandler("debug.log", maxBytes=1024 * 1024 * 400  # 400MB
                                                            , backupCount=10)
        handler_file.setFormatter(formatter)
        main_logger.addHandler(handler_file)

    main_logger.setLevel(logging.DEBUG)

    return main_logger

#=============================================================================


def distance(x, y, type='euclidian', x_weight=1.0, y_weight=1.0):
    if type == 'euclidian':
        return math.sqrt(float((x[0] - y[0])**2) / x_weight + float((x[1] - y[1])**2) / y_weight)


def get_centroid(x, y, w, h):
    x1 = int(w / 2)
    y1 = int(h / 2)

    cx = x + x1
    cy = y + y1

    return (cx, cy)


def skeleton(img):
    ret, img = cv2.threshold(img, 127, 255, 0)
    element = cv2.getStructuringElement(cv2.MORPH_CROSS, (3, 3))
    done = False
    size = np.size(img)
    skel = np.zeros(img.shape, np.uint8)
    while(not done):
        eroded = cv2.erode(img, element)
        temp = cv2.dilate(eroded, element)
        temp = cv2.subtract(img, temp)
        skel = cv2.bitwise_or(skel, temp)
        img = eroded.copy()
        zeros = size - cv2.countNonZero(img)
        if zeros == size:
            done = True

    return skel

def convert_frames_to_video(pathIn,pathOut,fps):
    frame_array = []
    files = [f for f in os.listdir(pathIn) if (isfile(join(pathIn, f)) and 'pro' in f)]
 
    #for sorting the file names properly
    # files.sort(key = lambda x: int(x[5:-4]))
 
    # for i in range(len(files)):
    for i, f in enumerate(files):
        filename = pathIn + f
        #reading each files
        print(filename)
        img = cv2.imread(filename)
        height, width, layers = img.shape
        size = (width,height)
        print(filename)
        #inserting the frames into an image array
        frame_array.append(img)
        if i == 900:
            break
 
    out = cv2.VideoWriter(pathOut,cv2.VideoWriter_fourcc(*'DIVX'), fps, size)
 
    for i in range(len(frame_array)):
        # writing to a image array
        out.write(frame_array[i])
    out.release()