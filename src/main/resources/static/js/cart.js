const DB = "product";

updateItemCount();

function addItemToDb(id) {
    let items = getAllItemsFromDb();
    if (items.length < 1) {
        items.push({
            id : id,
            count : 1
        });
        saveItemToDb(items);
    } else {
        const indx = items.findIndex(x => x.id === id);
        if (indx === -1) {
            items.push({
               id : id,
                count : 1
            });
        } else {
            items[indx].count = items[indx].count + 1;
        }
        saveItemToDb(items);
    }
}

function getAllItemsFromDb() {
    let items = localStorage.getItem(DB);
    if (items == null) {
        return [];
    }
    return JSON.parse(items);
}

function clearDb() {
    localStorage.removeItem(DB);
}

function updateItemCount() {
    let items = getAllItemsFromDb();
    document.getElementById("cartCount").innerHTML = items.length;
}

function saveItemToDb(items) {
    clearDb();
    localStorage.setItem(DB, JSON.stringify(items));
    updateItemCount();
}
