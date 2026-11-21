
# Application Monitoring Notes

## 1. Monitoring Architecture

The application uses Spring Boot Actuator and Micrometer to expose application metrics through the `/actuator/prometheus` endpoint.

Prometheus collects these metrics every 5 seconds using the configuration in `monitoring/prometheus.yml`.

Grafana connects to Prometheus and displays the collected metrics in a monitoring dashboard.

The monitoring architecture consists of:

- **Spring Boot:** Runs the Rent A Car application.
- **Spring Boot Actuator:** Exposes application health and monitoring endpoints.
- **Micrometer Prometheus Registry:** Makes application metrics available in Prometheus format.
- **Prometheus:** Collects and stores application metrics.
- **Grafana:** Visualizes the metrics through dashboard panels.
- **Docker Compose:** Runs Prometheus and Grafana containers.

## 2. Selected Metrics

The Grafana dashboard contains four monitoring panels:

### JVM Memory Used
Metric: `jvm_memory_used_bytes`

Shows the amount of memory currently used by the Java Virtual Machine.

### CPU Usage
Metric: `process_cpu_usage`

Shows the CPU usage of the Spring Boot application process.

### HTTP Request Rate
Metric: `sum(rate(http_server_requests_seconds_count[1m]))`

Shows the average number of HTTP requests handled per second over the last minute.

### HTTP Response Time (p95)
Metric: `histogram_quantile(0.95, sum by (le) (rate(http_server_requests_seconds_bucket[5m])))`

Shows the 95th percentile HTTP response time, indicating how long most requests take to complete.

## 3. Issue Resolved

During the initial Prometheus configuration, the `autoloc-api` target showed a DOWN status because the Prometheus scrape request received an HTTP 401 Unauthorized response.

The issue was caused by Spring Security requiring authentication for the Actuator endpoints.

To resolve it, the `/actuator/health` and `/actuator/prometheus` endpoints were added to the permitted request matchers in the Spring Security configuration.

After restarting the Spring Boot application, Prometheus successfully scraped the metrics endpoint and the `autoloc-api` target changed to UP.

## 4. Conclusion

The monitoring setup was successfully implemented using Spring Boot Actuator, Prometheus, Grafana and Docker Compose.

The Grafana dashboard displays JVM memory usage, CPU usage, HTTP request rate and HTTP response time. Postman was used to generate API requests, and the resulting activity was observed in Grafana.

**Security note:** The Actuator endpoints are publicly accessible in this lab configuration. In a production environment, access should be restricted and sensitive health details should not be exposed publicly.
