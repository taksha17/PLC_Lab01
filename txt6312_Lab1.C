// Name : Taksha Sachin Thosani
// UTA ID : 1002086312
// Lang Ver : 6.3.0
// OS : Windows 11

#include <stdio.h>
#include <sys/stat.h>
#include <dirent.h>
#include <string.h>

unsigned long long txt6312_cal_size(const char *a);

int main() {
    const char *dir = ".";  // Current directory
    unsigned long long sz = txt6312_cal_size(dir);
    printf("Total size: %llu bytes\n", sz);
    return 0;
}

unsigned long long txt6312_cal_size(const char *a) {
    unsigned long long b = 0;
    DIR *c = opendir(a);
    struct dirent *d;
    struct stat e;
    char f[1024];

    if (!c) {
        perror("opendir");
        return 0;
    }

    while ((d = readdir(c)) != NULL) {
        if (strcmp(d->d_name, ".") == 0 || strcmp(d->d_name, "..") == 0)
            continue;

        snprintf(f, sizeof(f), "%s/%s", a, d->d_name);
        if (stat(f, &e) == -1) {
            perror("stat");
            continue;
        }

        if (S_ISDIR(e.st_mode)) {
            b += txt6312_cal_size(f);
        } else if (S_ISREG(e.st_mode)) {
            b += e.st_size;
        }
    }

    closedir(c);
    return b;
}
