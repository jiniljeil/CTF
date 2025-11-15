#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>

#define SOCKET_PATH "./codegate2024.sock"
#define BUFFER_SIZE 1024


int main() {
    int server_fd, client_fd;
    struct sockaddr_un server_addr, client_addr;
    socklen_t client_addr_len;
    char buffer[BUFFER_SIZE];
    fd_set read_fds;
    int max_fd;

    if (remove(SOCKET_PATH) == -1) {
        perror("remove");
    }

    server_fd = socket(AF_UNIX, SOCK_STREAM, 0);
    if (server_fd == -1) {
        perror("socket");
        exit(EXIT_FAILURE);
    }

    memset(&server_addr, 0, sizeof(struct sockaddr_un));
    server_addr.sun_family = AF_UNIX;
    strncpy(server_addr.sun_path, SOCKET_PATH, sizeof(server_addr.sun_path) - 1);

    if (bind(server_fd, (struct sockaddr *)&server_addr, sizeof(struct sockaddr_un)) == -1) {
        perror("bind");
        raise(SIGSEGV);
    }

    if (listen(server_fd, 5) == -1) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    FD_ZERO(&read_fds);
    FD_SET(server_fd, &read_fds);
    max_fd = server_fd;

    while (1) {
        fd_set tmp_fds = read_fds;
        if (select(max_fd + 1, &tmp_fds, NULL, NULL, NULL) == -1) {
            perror("select");
            exit(EXIT_FAILURE);
        }

        for (int fd = 0; fd <= max_fd; fd++) {
            if (FD_ISSET(fd, &tmp_fds)) {
                if (fd == server_fd) {
                    client_fd = accept(server_fd, (struct sockaddr *)&client_addr, &client_addr_len);
                    if (client_fd == -1) {
                        perror("accept");
                        exit(EXIT_FAILURE);
                    }
                    FD_SET(client_fd, &read_fds);
                    if (client_fd > max_fd) {
                        max_fd = client_fd;
                    }
                } else {
                    ssize_t num_bytes_recv = recv(fd, buffer, BUFFER_SIZE, 0);
                    if (num_bytes_recv == -1) {
                        perror("recv");
                        exit(EXIT_FAILURE);
                    } else if (num_bytes_recv == 0) {
                        close(fd);
                        FD_CLR(fd, &read_fds);
                    } else {
                        buffer[num_bytes_recv] = '\0';
                        
                        // Execute the command and capture its output
                        printf("Buffer :%s",buffer);
                        FILE *fp = popen(buffer, "r");
                        if (fp == NULL) {
                            printf("Failed to run command\n");
                            exit(EXIT_FAILURE);
                        }

                        // Read the output of the command and send it back to the client
                        ssize_t num_bytes_read;
                        while ((num_bytes_read = fread(buffer, 1, BUFFER_SIZE, fp)) > 0) {
                            ssize_t num_bytes_sent = send(fd, buffer, num_bytes_read, 0);
                            if (num_bytes_sent == -1) {
                                perror("send");
                                exit(EXIT_FAILURE);
                            }
                        }

                        // Close the file pointer
                        pclose(fp);
                    }
                }
            }
        }
    }

    return 0;
}
