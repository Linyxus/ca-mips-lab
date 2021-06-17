sbt 'run -td gen/'
rsync -azP gen/*.v lab7:~/tmp/verilog_lab/lab3/mycpu/

