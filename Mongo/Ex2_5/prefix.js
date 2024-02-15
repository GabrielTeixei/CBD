prefix = function() {
    return db.phones.aggregate([{$group: {_id: "$components.prefix", sumEach: {$sum: 1}}}])
}