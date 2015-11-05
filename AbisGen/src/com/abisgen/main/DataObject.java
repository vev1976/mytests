package com.abisgen.main;

import java.io.PrintWriter;

public interface DataObject {
    public void generate_values();
    public void save_to_file(PrintWriter pw);
}
