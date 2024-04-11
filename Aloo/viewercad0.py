import tkinter as tk
from tkinter import ttk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
import numpy as np

class CADSoftware:
    def __init__(self, root):
        self.root = root
        self.root.title("Einfache CAD-Software")

        self.canvas = tk.Canvas(root, width=800, height=600)
        self.canvas.pack(side=tk.LEFT)

        self.init_3d_plot()

        self.create_ui()

    def init_3d_plot(self):
        self.fig = plt.Figure()
        self.ax = self.fig.add_subplot(111, projection='3d')
        self.ax.set_xlabel('X-Achse')
        self.ax.set_ylabel('Y-Achse')
        self.ax.set_zlabel('Z-Achse')

        self.canvas_3d = FigureCanvasTkAgg(self.fig, master=self.root)
        self.canvas_3d.get_tk_widget().pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)

    def create_ui(self):
        # UI-Elemente
        self.shape_var = tk.StringVar()
        self.shape_var.set("Quader")

        shape_label = tk.Label(self.canvas, text="Form auswählen:")
        shape_label.grid(row=0, column=0, padx=10, pady=10)

        shape_combobox = ttk.Combobox(self.canvas, textvariable=self.shape_var, values=["Quader"])
        shape_combobox.grid(row=0, column=1, padx=10, pady=10)

        add_button = tk.Button(self.canvas, text="Hinzufügen", command=self.add_shape)
        add_button.grid(row=0, column=2, padx=10, pady=10)

        # Punkte-Eingabe
        x_label = tk.Label(self.canvas, text="X:"
        x_label.grid(row=1, column=0, padx=10, pady=5)
        self.x_entry = tk.Entry(self.canvas)
        self.x_entry.grid(row=1, column=1, padx=10, pady=5)

        y_label = tk.Label(self.canvas, text="Y:")
        y_label.grid(row=1, column=2, padx=10, pady=5)
        self.y_entry = tk.Entry(self.canvas)
        self.y_entry.grid(row=1, column=3, padx=10, pady=5)

        z_label = tk.Label(self.canvas, text="Z:")
        z_label.grid(row=1, column=4, padx=10, pady=5)
        self.z_entry = tk.Entry(self.canvas)
        self.z_entry.grid(row=1, column=5, padx=10, pady=5)

    def add_shape(self):
        shape_type = self.shape_var.get()
        x = float(self.x_entry.get())
        y = float(self.y_entry.get())
        z = float(self.z_entry.get())

        if shape_type == "Quader":
            self.plot_cuboid(x, y, z)

    def plot_cuboid(self, x, y, z):
        # Koordinaten für die Ecken des Quaders
        vertices = np.array([
            [0, 0, 0],
            [x, 0, 0],
            [x, y, 0],
            [0, y, 0],
            [0, 0, z],
            [x, 0, z],
            [x, y, z],
            [0, y, z]
        ])

        # Verbindungslinien zwischen den Ecken
        lines = [
            [0, 1], [1, 2], [2, 3], [3, 0],
            [4, 5], [5, 6], [6, 7], [7, 4],
            [0, 4], [1, 5], [2, 6], [3, 7]
        ]

        for line in lines:
            self.ax.plot3D(*zip(*vertices[line]), color='b')

        self.canvas_3d.draw()

if __name__ == "__main__":
    root = tk.Tk()
    app = CADSoftware(root)
    root.mainloop()
