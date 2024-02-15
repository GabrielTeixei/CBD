function findCapicuas() {
    var fullNumber = db.phones.find({}, {"display": 1, "_id": 0}).toArray();
    var capicuas = [];

    for (var index = 0; index < fullNumber.length; index++) {
        var element = fullNumber[index].display.split("-")[1];
        if (element === element.split("").reverse().join("")) {
            capicuas.push(fullNumber[index]);
        }
    }

    return capicuas;
}
