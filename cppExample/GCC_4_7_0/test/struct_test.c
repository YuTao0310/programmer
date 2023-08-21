#include <stdio.h>

struct point {
    int x;
    int y;
};

struct point add_two_point(struct point point1, struct point point2);

int main(int argc, int *args[]) {
    struct point point1 = {1, 2};
    struct point point2 = {3, 4};
    struct point res = add_two_point(point1, point2);
    printf("point1.x: %d, point1.y: %d \n", point1.x, point1.y);
    printf("res.x: %d, res.y: %d\n", res.x, res.y);
}

struct point add_two_point(struct point point1, struct point point2) {
    point1.x += point2.x;
    point1.y += point2.y;
    return point1;
}