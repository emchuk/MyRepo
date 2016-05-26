package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == r.getUuid()) {
                storage[i] = r;
                System.out.println(i);
                break;
            }
            if (storage[i].getUuid() != r.getUuid() && i == size - 1) {
                System.out.println("ERROR");
            }
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("The Storage is FULL!");
        }
        for (int i = 0; i<size; i++){
            if(storage[i].getUuid() == r.getUuid()){
                System.out.println("ERROR");
            }
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid() && present(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid() && present(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] all = Arrays.copyOfRange(storage, 0, size);
        return all;
    }

    public int size() {
        return size;
    }

    public boolean present(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                return true;
            }
        }
        return false;
    }
}
