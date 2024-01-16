import matplotlib.pyplot as plt
import pandas as pd


data = pd.read_csv("fires_thefts.csv", names=["x", "y"])
x = data["x"].to_numpy()
y = data["y"].to_numpy()


# Hipoteza: funkcja liniowa jednej zmiennej
def h(theta, x):
    return theta[0] + theta[1] * x


def J(h, theta, x, y):
    """Funkcja kosztu"""
    m = len(y)
    return 1.0 / (2 * m) * sum((h(theta, x[i]) - y[i]) ** 2 for i in range(m))


def gradient_descent(h, cost_fun, theta, x, y, alpha, eps):
    current_cost = cost_fun(h, theta, x, y)
    history = [
        [current_cost, theta]
    ]  # zapiszmy wartości kosztu i parametrów, by potem zrobić wykres
    m = len(y)
    while True:
        new_theta = [
            theta[0] - alpha / float(m) * sum(h(theta, x[i]) - y[i] for i in range(m)),
            theta[1]
            - alpha / float(m) * sum((h(theta, x[i]) - y[i]) * x[i] for i in range(m)),
        ]
        theta = new_theta  # jednoczesna aktualizacja - używamy zmiennej tymczasowej
        try:
            prev_cost = current_cost
            current_cost = cost_fun(h, theta, x, y)
        except OverflowError:
            break
        if abs(prev_cost - current_cost) <= eps:
            break
        history.append([current_cost, theta])
    return theta, history


# chart
epsilon_values = []
theta_list = []

for i in range(4):
    epsilon_values.append(str(0.01 * 10**(-i)))
    best_theta, history = gradient_descent(h, J, [0.0, 0.0], x, y, alpha=0.001, eps=0.01*10**(-i))
    theta_list.append(history[-1][0])

fig, ax = plt.subplots()
bar_container = ax.bar(epsilon_values, theta_list)
ax.set(ylabel='cost', ylim=(170, 200))
ax.set(xlabel='epsilon', title='cost when α = 0.001')
ax.bar_label(bar_container, fmt=lambda cost: f'{round(cost, 4)}')
plt.show()

# gradient descent (alfa = 0.001 and epsilon = 0.0001 due to accuracy and speed)
best_theta, history = gradient_descent(h, J, [0.0, 0.0], x, y, alpha=0.001, eps=0.0001)
# alpha=0.001,  eps=0.01:      [8.2432; 1.7543]     J(theta) = 194.2673     1822 iterations
# alpha=0.001,  eps=0.001:      [14.2268; 1.4529]     J(theta) = 181.7965     5013 it.
# alpha=0.001,  eps=0.0001:      [16.1198; 1.3575]     J(theta) = 180.5487     8205 it.
# alpha=0.001,  eps=0.00001:      [16.7184; 1.3274]     J(theta) = 180.4239     11397 it.

# alpha=0.001,  eps=0.000001:      [14.2260; 1.4529]     J(theta) = 180.4115     14588 it.
# alpha=0.0001, eps=0.0001:        [14.2260; 1.4529]     J(theta) = 181.7964     50130 it.
# alpha=0.0001, eps=0.00001:       [16.1195; 1.3576]     J(theta) = 180.5487     82052 it.
# alpha=0.0001, eps=0.000001:      [16.7182; 1.3274]     J(theta) = 180.4239     113975 it.

# results
print(f'θ = [{round(best_theta[0], 4)}; {round(best_theta[1], 4)}]')
print(f'J(θ) = {round(history[-1][0], 4)}')
print(f'Iterations: {len(history)}\n')

# prediction
print('Predicted break-ins/1000 pers. when number of fires is:')
print(f'- 50: {round(h(best_theta, 50), 4)}')
print(f'- 100: {round(h(best_theta, 100), 4)}')
print(f'- 200: {round(h(best_theta, 200), 4)}')
