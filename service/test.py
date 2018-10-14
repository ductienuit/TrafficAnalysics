from model import Model

m = Model()

m.load_data('./data/traffic.csv')

m.train()