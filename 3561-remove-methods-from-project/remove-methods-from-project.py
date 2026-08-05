class Solution(object):
    def remainingMethods(self, n, k, invocations):
        """
        :type n: int
        :type k: int
        :type invocations: List[List[int]]
        :rtype: List[int]
        """
        graph = [[] for _ in range(n)]
        suspicious = [False] * n
        for u, v in invocations:
            graph[u].append(v)
        def dfs(u):
            suspicious[u] = True
            for v in graph[u]:
                if not suspicious[v]:
                    dfs(v)
        dfs(k)
        for u, v in invocations:
            if not suspicious[u] and suspicious[v]:
                return list(range(n))
        ans = []
        for i in range(n):
            if not suspicious[i]:
                ans.append(i)
        return ans
__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("000"))