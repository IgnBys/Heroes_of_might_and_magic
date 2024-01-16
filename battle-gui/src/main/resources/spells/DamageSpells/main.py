import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
import matplotlib.pyplot as plt
import numpy as np


df = pd.read_csv('data6.tsv', header=None, sep='\t')

X = df.iloc[:, 0].values.reshape(-1, 1)
y = df.iloc[:, 1].values

# regresja 1 st.
lin = LinearRegression()
lin.fit(X, y)

# regresja 2 st.
poly_2 = PolynomialFeatures(degree=2, include_bias=False)
x_poly_5 = poly_2.fit_transform(X)

lin2 = LinearRegression()
lin2.fit(x_poly_5, y)

# regresja 5 st.
poly_5 = PolynomialFeatures(degree=5, include_bias=False)
x_poly_5 = poly_5.fit_transform(X)

lin5 = LinearRegression()
lin5.fit(x_poly_5, y)

X_range = np.linspace(X.min(), X.max(), 100).reshape(-1, 1)

plt.scatter(X, y, color='red', label='Dane')
plt.plot(X, lin.predict(X), color='orange', label='Regresja pierwszego st.')
plt.plot(X_range, lin2.predict(poly_2.transform(X_range)), color='blue', label='Regresja drugiego st.')
plt.plot(X_range, lin5.predict(poly_5.transform(X_range)), color='green', label='Regresja piątego st.')

plt.title('Regresja wielomianowa')
plt.legend()
plt.show()
