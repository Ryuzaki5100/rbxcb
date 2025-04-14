package com.cube.demo.rbxcb.rbxcb_3x3x3;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class StageSolver {
    public static String solve(Cube c, String[] moveRestrictions, StageMasker sm) {
        Cube source = c.clone();
        Cube destination = new Cube();

        if (sm.mask(source).equals(sm.mask(destination)))
            return "";

        class Temp {
            final Cube c;
            final String s;

            public Temp(Cube c, String s) {
                this.c = c.clone();
                this.s = new String(s);
            }
        }

        Queue<Temp> forward = new LinkedBlockingQueue<>();
        Queue<Temp> backward = new LinkedBlockingQueue<>();

        ConcurrentHashMap<ArrayList<Integer>, String> forwardSolution = new ConcurrentHashMap<>();
        ConcurrentHashMap<ArrayList<Integer>, String> backwardSolution = new ConcurrentHashMap<>();

        forwardSolution.put(sm.mask(source), "");
        backwardSolution.put(sm.mask(destination), "");

        forward.add(new Temp(source, ""));
        backward.add(new Temp(destination, ""));

        // Shared variable to store the result
        AtomicReference<String> result = new AtomicReference<>(null);
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Forward search task
        Runnable forwardSearch = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Temp f = forward.poll();
                    if (f == null) continue;

                    for (String move : moveRestrictions) {
                        Cube temp = Cube.execute(f.c, move);
                        ArrayList<Integer> maskedState = sm.mask(temp);

                        if (backwardSolution.containsKey(maskedState)) {
                            result.set(f.s + move + Cube.reverseAlgorithm(backwardSolution.get(maskedState)));
                            latch.countDown(); // Signal that a solution was found
                            return;
                        }

                        if (!forwardSolution.containsKey(maskedState)) {
                            forwardSolution.put(maskedState, f.s + move);
                            forward.add(new Temp(temp, f.s + move));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Backward search task
        Runnable backwardSearch = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Temp b = backward.poll();
                    if (b == null) continue;

                    for (String move : moveRestrictions) {
                        Cube temp = Cube.execute(b.c, move);
                        ArrayList<Integer> maskedState = sm.mask(temp);

                        if (forwardSolution.containsKey(maskedState)) {
                            result.set(forwardSolution.get(maskedState) + Cube.reverseAlgorithm(b.s + move));
                            latch.countDown(); // Signal that a solution was found
                            return;
                        }

                        if (!backwardSolution.containsKey(maskedState)) {
                            backwardSolution.put(maskedState, b.s + move);
                            backward.add(new Temp(temp, b.s + move));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Submit both tasks to the executor
        executor.submit(forwardSearch);
        executor.submit(backwardSearch);

        try {
            latch.await(); // Wait for one thread to find a solution
            executor.shutdownNow(); // Stop all threads once a solution is found
            return result.get(); // Return the found solution
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Ensure the executor is properly shut down
        }

        return null; // In case no solution is found
    }

    public static ArrayList<String> solve(Cube c, String[] moveRestrictions, StageMasker sm, int numSolutions) {
        Cube source = c.clone();
        Cube destination = new Cube();

        if (sm.mask(source).equals(sm.mask(destination)))
            return new ArrayList<>(Collections.singletonList(""));

        class Temp {
            final Cube c;
            final String s;

            public Temp(Cube c, String s) {
                this.c = c.clone();
                this.s = new String(s);
            }
        }

        Queue<Temp> forward = new LinkedBlockingQueue<>();
        Queue<Temp> backward = new LinkedBlockingQueue<>();

        ConcurrentHashMap<ArrayList<Integer>, String> forwardSolution = new ConcurrentHashMap<>();
        ConcurrentHashMap<ArrayList<Integer>, String> backwardSolution = new ConcurrentHashMap<>();

        forwardSolution.put(sm.mask(source), "");
        backwardSolution.put(sm.mask(destination), "");

        forward.add(new Temp(source, ""));
        backward.add(new Temp(destination, ""));

        // Shared variable to store multiple solutions
        ConcurrentLinkedQueue<String> solutions = new ConcurrentLinkedQueue<>();
        AtomicInteger solutionCount = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Forward search task
        Runnable forwardSearch = () -> {
            try {
                while (!Thread.currentThread().isInterrupted() && solutionCount.get() < numSolutions) {
                    Temp f = forward.poll();
                    if (f == null) continue;

                    for (String move : moveRestrictions) {
                        Cube temp = Cube.execute(f.c, move);
                        ArrayList<Integer> maskedState = sm.mask(temp);

                        if (backwardSolution.containsKey(maskedState)) {
                            String solution = f.s + move + Cube.reverseAlgorithm(backwardSolution.get(maskedState));
                            solutions.add(solution);
                            if (solutionCount.incrementAndGet() >= numSolutions) {
                                latch.countDown();
                                return;
                            }
                        }

                        if (!forwardSolution.containsKey(maskedState)) {
                            forwardSolution.put(maskedState, f.s + move);
                            forward.add(new Temp(temp, f.s + move));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Backward search task
        Runnable backwardSearch = () -> {
            try {
                while (!Thread.currentThread().isInterrupted() && solutionCount.get() < numSolutions) {
                    Temp b = backward.poll();
                    if (b == null) continue;

                    for (String move : moveRestrictions) {
                        Cube temp = Cube.execute(b.c, move);
                        ArrayList<Integer> maskedState = sm.mask(temp);

                        if (forwardSolution.containsKey(maskedState)) {
                            String solution = forwardSolution.get(maskedState) + Cube.reverseAlgorithm(b.s + move);
                            solutions.add(solution);
                            if (solutionCount.incrementAndGet() >= numSolutions) {
                                latch.countDown();
                                return;
                            }
                        }

                        if (!backwardSolution.containsKey(maskedState)) {
                            backwardSolution.put(maskedState, b.s + move);
                            backward.add(new Temp(temp, b.s + move));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        executor.submit(forwardSearch);
        executor.submit(backwardSearch);

        try {
            latch.await();
            executor.shutdownNow();
            return new ArrayList<>(solutions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return new ArrayList<>(solutions);
    }


//    public static String solve(Cube c, String[] stagedMoveRestrictions, StageMasker sm) {
//        Cube source = c.clone();
//        Cube destination = new Cube();
//
//        class Temp {
//            final Cube c;
//            final String s;
//
//            public Temp(Cube c, String s) {
//                this.c = c.clone();
//                this.s = new String(s);
//            }
//        }
//
//        Queue<Temp> forward = new LinkedBlockingQueue<>();
//        Queue<Temp> backward = new LinkedBlockingQueue<>();
//
//        HashMap<ArrayList<Integer>, String> forwardSolution = new HashMap<>();
//        HashMap<ArrayList<Integer>, String> backwardSolution = new HashMap<>();
//
//        forwardSolution.put(sm.mask(source), "");
//        backwardSolution.put(sm.mask(destination), "");
//
//        forward.add(new Temp(source, ""));
//        backward.add(new Temp(destination, ""));
//
//        while (true) {
//            Temp f = forward.poll();
//
//            for (String stagedMoveRestriction : stagedMoveRestrictions) {
//                Cube temp = Cube.execute(f.c, stagedMoveRestriction);
//
//                ArrayList<Integer> x = sm.mask(temp);
//
//                if (backwardSolution.containsKey(x))
//                    return f.s + stagedMoveRestriction + Cube.reverseAlgorithm(backwardSolution.get(x));
//
//
//                if (!forwardSolution.containsKey(x)) {
//                    forwardSolution.put(x, f.s + stagedMoveRestriction);
//                    forward.add(new Temp(temp, f.s + stagedMoveRestriction));
//                }
//            }
//
//            Temp b = backward.poll();
//
//            for (String stagedMoveRestriction : stagedMoveRestrictions) {
//                Cube temp = Cube.execute(b.c, stagedMoveRestriction);
//
//                ArrayList<Integer> x = sm.mask(temp);
//
//                if (forwardSolution.containsKey(x))
//                    return forwardSolution.get(x) + Cube.reverseAlgorithm(b.s + stagedMoveRestriction);
//
//                if (!backwardSolution.containsKey(x)) {
//                    backwardSolution.put(x, b.s + stagedMoveRestriction);
//                    backward.add(new Temp(temp, b.s + stagedMoveRestriction));
//                }
//            }
//        }
//    }


//    public String solveStage(String[] stagedMoveRestrictions, StageMasker sm) {
//        Cube source = new Cube(this);
//        Cube destination = new Cube();
//
//        class Temp {
//            final Cube c;
//            final String s;
//
//            public Temp(Cube c, String s) {
//                this.c = c.clone();
//                this.s = new String(s);
//            }
//        }
//
//        Queue<Temp> forward = new LinkedBlockingQueue<>();
//        Queue<Temp> backward = new LinkedBlockingQueue<>();
//
//        Map<ArrayList<Integer>, String> forwardSolution = Collections.synchronizedMap(new HashMap<>());
//        Map<ArrayList<Integer>, String> backwardSolution = Collections.synchronizedMap(new HashMap<>());
//
//        forwardSolution.put(sm.mask(source), "");
//        backwardSolution.put(sm.mask(destination), "");
//
//        forward.add(new Temp(source, ""));
//        backward.add(new Temp(destination, ""));
//
//        // Create a shared flag to signal when a solution is found
//        AtomicReference<String> solution = new AtomicReference<>(null);
//        AtomicBoolean found = new AtomicBoolean(false);
//
//        // Forward search thread
//        Thread forwardThread = new Thread(() -> {
//            while (!found.get()) {
//                Temp f = forward.poll();
//                if (f == null) continue;
//
//                for (String stagedMoveRestriction : stagedMoveRestrictions) {
//                    Cube temp = Cube.execute(f.c, stagedMoveRestriction);
//                    ArrayList<Integer> x = sm.mask(temp);
//
//                    // Check for intersection
//                    if (backwardSolution.containsKey(x)) {
//                        solution.set(f.s + stagedMoveRestriction + Cube.reverseAlgorithm(backwardSolution.get(x)));
//                        found.set(true);
//                        return;
//                    }
//
//                    // Add new state if not already visited
//                    if (!forwardSolution.containsKey(x)) {
//                        forwardSolution.put(x, f.s + stagedMoveRestriction);
//                        forward.add(new Temp(temp, f.s + stagedMoveRestriction));
//                    }
//                }
//            }
//        });
//
//        // Backward search thread
//        Thread backwardThread = new Thread(() -> {
//            while (!found.get()) {
//                Temp b = backward.poll();
//                if (b == null) continue;
//
//                for (String stagedMoveRestriction : stagedMoveRestrictions) {
//                    Cube temp = Cube.execute(b.c, stagedMoveRestriction);
//                    ArrayList<Integer> x = sm.mask(temp);
//
//                    // Check for intersection
//                    if (forwardSolution.containsKey(x)) {
//                        solution.set(forwardSolution.get(x) + Cube.reverseAlgorithm(b.s + stagedMoveRestriction));
//                        found.set(true);
//                        return;
//                    }
//
//                    // Add new state if not already visited
//                    if (!backwardSolution.containsKey(x)) {
//                        backwardSolution.put(x, b.s + stagedMoveRestriction);
//                        backward.add(new Temp(temp, b.s + stagedMoveRestriction));
//                    }
//                }
//            }
//        });
//
//        // Start both threads
//        forwardThread.start();
//        backwardThread.start();
//
//        // Wait for both threads to complete
//        try {
//            forwardThread.join();
//            backwardThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//x
//        // Return the solution found
//        return solution.get();
//    }

}
