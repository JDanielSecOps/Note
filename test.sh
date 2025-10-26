
#!/bin/bash

unset GTK_PATH

#gcc -rdynamic -g main.c -o "Todo App" $(pkg-config --cflags --libs gtk+-3.0); 


if gdb -batch -ex run -ex bt --args ./'Todo App'; then

    ./'Todo App'
    echo "Build Successful"

else

    echo "Build Failed"

fi