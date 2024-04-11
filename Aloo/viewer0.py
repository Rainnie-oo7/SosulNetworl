import pygame
from pygame.locals import *

pygame.init()

width, height = 800, 600
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("3D Viewer")

clock = pygame.time.Clock()

cube_vertices = [
    (100, 100, 100), (-100, 100, 100), (-100, -100, 100), (100, -100, 100),
    (100, 100, -100), (-100, 100, -100), (-100, -100, -100), (100, -100, -100)
]

cube_edges = [
    (0, 1), (1, 2), (2, 3), (3, 0),
    (4, 5), (5, 6), (6, 7), (7, 4),
    (0, 4), (1, 5), (2, 6), (3, 7)
]

def draw_cube():
    for edge in cube_edges:
        pygame.draw.line(screen, (255, 255, 255), project(cube_vertices[edge[0]]), project(cube_vertices[edge[1]]), 2)

def project(vertex):
    x, y, z = vertex
    f = 200  # focal length
    scale = 2  # scaling factor
    return int(x * f / (z + f) * scale + width / 2), int(y * f / (z + f) * scale + height / 2)

running = True
while running:
    for event in pygame.event.get():
        if event.type == QUIT:
            running = False

    screen.fill((0, 0, 0))
    draw_cube()

    pygame.display.flip()
    clock.tick(30)

pygame.quit()