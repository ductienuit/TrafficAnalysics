import os
import sklearn as sk
import tensorflow as tf
import pandas as pd

class Model:
    def __init__(self, l_rate = 0.0001, epoch = 300):
        self.a = tf.Variable(tf.random_normal([1]), dtype=tf.int32)
        self.c = tf.Variable(tf.random_normal([1]), dtype=tf.int32)
        self.b = tf.Variable(tf.random_normal([1]), dtype=tf.int32)
        self.d = tf.Variable(tf.random_normal([1]), dtype=tf.int32)
        self.e = tf.Variable(tf.random_normal([1]), dtype=tf.int32)
        self.learning_rate = l_rate
        self.epoch = 300

    def predict(self, time, location):
        
        return 'Yes'

    def cost_function(self, X, Y, n_sample):
        h = self.a * X**4 + self.b * X**3 + self.c * X**2 + self.d * X + self.e
        cost = tf.reduce_sum((h - Y)**2 / (2 * n_sample))
        return cost
    
    def load_data(self, dataDir):
        df = pd.read_csv(dataDir)
        self.data = df.drop(['status'], axis=1)
        self.labels = df['status']
    
    def define_optimizer(self, cost_func):
        optimizer = tf.train.GradientDescentOptimizer(self.learning_rate).minimize(cost_func)
        initializer = tf.global_variables_initializer()
        return optimizer, initializer

    def train(self):
        n_sample = len(self.data)

        X = tf.placeholder(tf.int32)
        Y = tf.placeholder(tf.int32)

        cost = self.cost_function(X, Y, n_sample)
        opt, init = self.define_optimizer(cost)

        sess = tf.Session()
        sess.run(init)
        
        for i in range(self.epoch):
            _, cost_op = sess.run([opt, cost], feed_dict={X:self.data, Y: self.labels})
            a_op = sess.run(self.a)
            b_op = sess.run(self.b)
            c_op = sess.run(self.c)
            d_op = sess.run(self.d)
            e_op = sess.run(self.e)
            if i % 1000 == 50:
                print('a=', a_op, 'b=', b_op, 'c=', c_op, 'd=', d_op, 'e=', e_op , 'Cost = ', cost_op)

        with open('weights.txt', 'w') as w_file:
            w_file.write([self.a, self.b, self.c, self.d, self.e])
            print('Weight file saved!')
