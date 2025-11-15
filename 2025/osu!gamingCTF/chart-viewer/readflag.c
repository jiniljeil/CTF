/* readflag.c — minimal SUID reader (safer than system()) */
#define _GNU_SOURCE
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <errno.h>
#include <stdio.h>

int main(void) {
    if (setuid(0) != 0) {
        _exit(1);
    }

    /* setgroups(0, NULL); */ /* uncomment if desired and permitted */

    int fd = open("/flag.txt", O_RDONLY | O_CLOEXEC);
    if (fd < 0) _exit(2);

    /* Read and write loop */
    char buf[4096];
    ssize_t n;
    while ((n = read(fd, buf, sizeof(buf))) > 0) {
        ssize_t w = 0;
        while (w < n) {
            ssize_t s = write(1, buf + w, n - w);
            if (s <= 0) _exit(3);
            w += s;
        }
    }
    close(fd);
    _exit(0);
}
