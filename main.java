import java.util.*;

class Main
{
    static boolean[] SieveOfEratosThenes(int max_val)
    {
        boolean[] prime = new boolean[max_val + 1];
        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int p = 2; p * p <= max_val; p++)
        {

            if (prime[p])
            {
                for (int i = p * 2; i <= max_val; i += p)
                    prime[i] = false;
            }
        }
        return prime;
    }
    static void kMinXOR(Integer[] arr, int n, int k)
    {

        int max_val = Collections.max(Arrays.asList(arr));

        boolean[] prime = SieveOfEratosThenes(max_val);

        PriorityQueue<Integer> minHeapPrime = new PriorityQueue<>();
        PriorityQueue<Integer> minHeapNonPrime = new PriorityQueue<>();

        for (int i = 0; i < n; i++)
        {
            if (prime[arr[i]])
            {
                if (minHeapPrime.size() < k)
                    minHeapPrime.add(arr[i]);
                else if (minHeapPrime.peek() < arr[i])
                {
                    minHeapPrime.poll();
                    minHeapPrime.add(arr[i]);
                }
            }
            else if (arr[i] != -1)
            {
                if (minHeapNonPrime.size() < k)
                    minHeapNonPrime.add(arr[i]);
                else if (minHeapNonPrime.peek() < arr[i])
                {
                    minHeapNonPrime.poll();
                    minHeapNonPrime.add(arr[i]);
                }
            }
        }

        long primeXOR = 0, nonPrimeXor = 0;

        while (k-- > 0)
        {
            if (minHeapPrime.size() > 0)
            {
                primeXOR ^= minHeapPrime.peek();
                minHeapPrime.poll();
            }

            if (minHeapNonPrime.size() > 0)
            {
                nonPrimeXor ^= minHeapNonPrime.peek();
                minHeapNonPrime.poll();
            }
        }

        System.out.print("Prime XOR = " + primeXOR);
        System.out.println("Composite XOR = " + nonPrimeXor);
        System.out.println("Values have been printed");
    }
    public static void main(String[] args)
    {
        Integer[] arr = { 4, 2, 12, 13, 5, 19 };
        int n = arr.length;
        int k = 3;

        kMinXOR(arr, n, k);
    }
}



gcnmvnmdgxfbnn 
