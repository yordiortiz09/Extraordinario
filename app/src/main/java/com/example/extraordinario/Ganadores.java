package com.example.extraordinario;

import java.util.List;

public class Ganadores {
    String status;
    List<nombresGanadores> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<nombresGanadores> getData() {
        return data;
    }

    public void setData(List<nombresGanadores> data) {
        this.data = data;
    }

    public Ganadores(String status, List<nombresGanadores> data) {
        this.status = status;
        this.data = data;
    }
}
