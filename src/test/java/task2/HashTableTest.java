package task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void testInsertIntoEmptyTable() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        assertTrue(hashTable.contains(5));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[5] 6[] 7[] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testInsertWithCollision() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(15); // Коллизия с 5
        assertTrue(hashTable.contains(5));
        assertTrue(hashTable.contains(15));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[5] 6[15] 7[] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testMultipleCollisions() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(15);
        hashTable.insert(25); // Коллизия с 5 и 15
        assertTrue(hashTable.contains(5));
        assertTrue(hashTable.contains(15));
        assertTrue(hashTable.contains(25));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[5] 6[15] 7[25] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testSearchExistingElement() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(15);
        assertTrue(hashTable.contains(5));
        assertTrue(hashTable.contains(15));
    }

    @Test
    void testSearchNonExistingElement() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        assertFalse(hashTable.contains(10));
    }

    @Test
    void testDeleteElement() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(15);
        hashTable.delete(5);
        assertFalse(hashTable.contains(5));
        assertTrue(hashTable.contains(15));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[~5~] 6[15] 7[] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testDeleteInMiddleOfProbingChain() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(15);
        hashTable.insert(25); // Коллизия с 5 и 15
        hashTable.delete(15); // Удаляем элемент в середине цепочки
        assertTrue(hashTable.contains(5));
        assertFalse(hashTable.contains(15));
        assertTrue(hashTable.contains(25));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[5] 6[~15~] 7[25] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testReinsertAfterDelete() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(15);
        hashTable.delete(5);
        hashTable.insert(25); // Должно быть вставлено в ячейку, где был удален 5
        assertTrue(hashTable.contains(25));
        assertTrue(hashTable.contains(15));
        assertFalse(hashTable.contains(5));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[25] 6[15] 7[] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testInsertIntoFullTable() {
        HashTable hashTable = new HashTable(3);
        hashTable.insert(1);
        hashTable.insert(2);
        hashTable.insert(3); // Таблица заполнена
        assertThrows(IllegalStateException.class, () -> hashTable.insert(4)); // Попытка вставить в заполненную таблицу
    }

    @Test
    void testInsertExistingElement() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.insert(5);
        assertTrue(hashTable.contains(5));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[5] 6[5] 7[] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testDeleteNonExistingElement() {
        HashTable hashTable = new HashTable(10);
        hashTable.insert(5);
        hashTable.delete(10);
        assertTrue(hashTable.contains(5));
        assertEquals("0[] 1[] 2[] 3[] 4[] 5[5] 6[] 7[] 8[] 9[] ", hashTable.toString());
    }

    @Test
    void testInsertWithWrapAround() {
        HashTable hashTable = new HashTable(5);

        hashTable.insert(4); // 4 % 5 = 4
        hashTable.insert(9); // 9 % 5 = 4 -> коллизия, вставляем в ячейку 0
        hashTable.insert(14); // 14 % 5 = 4 -> коллизия, вставляем в ячейку 1

        assertTrue(hashTable.contains(4));
        assertTrue(hashTable.contains(9));
        assertTrue(hashTable.contains(14));

        assertEquals("0[9] 1[14] 2[] 3[] 4[4] ", hashTable.toString());
    }

    @Test
    void testDeleteStopsAtNull() {
        HashTable hashTable = new HashTable(3);
        hashTable.insert(0);
        hashTable.insert(3);
        hashTable.delete(3); // Индекс 1 помечен как удалённый
        hashTable.delete(6); // Хеш 6%3=0 → проверка индексов 0,1,2 (null)
        // Проверяем, что таблица не изменилась
        assertFalse(hashTable.contains(6));
        assertEquals("0[0] 1[~3~] 2[] ", hashTable.toString());
    }

    @Test
    void testContainsFullTableKeyAbsent() {
        HashTable hashTable = new HashTable(3);
        hashTable.insert(1);
        hashTable.insert(2);
        hashTable.insert(3);
        assertFalse(hashTable.contains(4));
    }

    @Test
    void testDeleteFullTableKeyAbsent() {
        HashTable hashTable = new HashTable(3);
        hashTable.insert(1);
        hashTable.insert(2);
        hashTable.insert(3);
        hashTable.delete(4);
        assertTrue(hashTable.contains(1));
        assertTrue(hashTable.contains(2));
        assertTrue(hashTable.contains(3));
    }

    @Test
    void testDeleteAlreadyDeleted() {
        HashTable hashTable = new HashTable(5);
        hashTable.insert(1);
        hashTable.delete(1);
        hashTable.delete(1);
        assertFalse(hashTable.contains(1));
    }
}