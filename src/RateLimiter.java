import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private final int maxTokens;
    private final long refillIntervalInMillis;
    private AtomicInteger tokens;
    private long lastRefillTimeStamp;


    public RateLimiter(int maxTokens, long refillIntervalInMillis) {
        this.maxTokens = maxTokens;
        this.refillIntervalInMillis = refillIntervalInMillis;
        this.tokens = new AtomicInteger(maxTokens);
        this.lastRefillTimeStamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest(){
        refill();

        if(tokens.get()>0){
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }

    public void refill(){
        long now = System.currentTimeMillis();
        long elapsed = now -lastRefillTimeStamp;

        if(elapsed> refillIntervalInMillis){
            int refillCount = (int)(elapsed/refillIntervalInMillis);
            int newTokens = Math.min(maxTokens,tokens.get()+ refillCount);
            tokens.set(newTokens);
            lastRefillTimeStamp= now;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(3, 1000);
        for (int i = 1; i <= 10; i++) {
            if (limiter.allowRequest()) {
                System.out.println("Request " + i + " -> Allowed ✅");
            } else {
                System.out.println("Request " + i + " -> Blocked ❌");
            }
            Thread.sleep(200); // 200ms gap between requests
        }
    }
}
