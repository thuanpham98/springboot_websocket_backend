function connect() {
    var socket = new WebSocket('ws://192.168.1.21:6969/gateway');
    ws = Stomp.over(socket);

    ws.connect({}, function(frame) {
        ws.subscrribe("/user/queue/errors", function(message) {
            alert("Error " + message.body);
        });

        ws.subscribe("/user/queue/reply", function(message) {
            alert("Message " + message.body);
        });
    }, function(error) {
        alert("STOMP error " + error);
    });
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}
