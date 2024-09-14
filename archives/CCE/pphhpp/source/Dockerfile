FROM ubuntu:20.04

ARG DEBIAN_FRONTEND=noninteractive

# Install Apache, PHP, and dependencies
RUN apt-get update && apt-get install -y apache2 libapache2-mod-php php-mysql curl php-curl

# Set permissions for Apache to access application files
RUN chown -R www-data:www-data /var/www/html/

# Expose port 80 for HTTP traffic
EXPOSE 80

# Start Apache server
CMD ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"]