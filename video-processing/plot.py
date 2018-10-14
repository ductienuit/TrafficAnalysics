"""
This file is used for drawing chart from data read from exported .csv file
"""


import pandas as pd
import matplotlib.pyplot as plt
import sys
import numpy as np

if len(sys.argv) >= 3:
    d = pd.read_csv(sys.argv[1], index_col=None)
    d['{} seconds'.format(int(sys.argv[2]))] = (d['time']/(int(sys.argv[2])*100)).astype(int)
    d = d.groupby('{} seconds'.format(int(sys.argv[2]))).sum()
    d = d.drop(['time'], axis=1)
    d.plot()
    # plt.imsave('result.png')
    plt.show()
else:
    print("Usage: python plot.py [path to the csv report] [number of seconds to aggregate by]")
