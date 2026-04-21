package com.example.demo.model.dto;

import java.util.List;

public class VulneratibilityReportsDTO {
  private List<VulnerabilityReportDTO> content;
  private int page;
  private int totalPages;
  private long totalElements;

  public VulneratibilityReportsDTO(List<VulnerabilityReportDTO> content, int page, int totalPages, long totalElements) {
    this.content = content;
    this.page = page;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

  public List<VulnerabilityReportDTO> getContent() {
    return content;
  }

  public int getPage() {
    return page;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }
}
