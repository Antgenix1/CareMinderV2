# Workflow

Dieses Dokument bietet eine detaillierte Beschreibung unserer Arbeitsweise in diesem Projekt. Es dient als Leitfaden für das Team, um sicherzustellen, dass wir effizient und effektiv zusammenarbeiten, um die Projektziele zu erreichen.

## Branching

### GitHub Flow Workflow

#### Übersicht

Der GitHub Flow ist ein leichtgewichtiges, branch-basiertes Workflow, der viele Teams auf der ganzen Welt lieben. Es ist ein sehr einfacher Workflow, der sich auf kontinuierliche Bereitstellung konzentriert und nur wenige strenge Regeln hat.

#### Branches

- **Main**: Der Main-Branch enthält den produktionsreifen Code. Es ist der Branch, der zur Produktion bereitgestellt wird.
- **Feature**: Feature-Branches werden verwendet, um neue Funktionen zu entwickeln oder Fehler zu beheben. Sie werden direkt vom Main-Branch erstellt und wieder in diesen zurückgeführt, sobald das Feature fertig ist oder der Fehler behoben ist.

### Pull Requests

Pull Requests (PRs) sind eine Möglichkeit, Änderungen an einem Codebase vorzuschlagen und mit anderen an diesen Änderungen zusammenzuarbeiten. Wenn Sie einen PR erstellen, bitten Sie jemand anderen, Ihre Änderungen zu überprüfen und sie in den Main-Branch zu integrieren.

#### Workflow

1. Erstellen Sie einen neuen Branch für Ihre Änderungen direkt vom Main-Branch.
2. Machen Sie Ihre Änderungen in diesem Branch.
3. Pushen Sie Ihre Änderungen zum Remote-Repository.
4. Erstellen Sie einen PR von Ihrem Branch zum Main-Branch.
5. Arbeiten Sie mit anderen zusammen, indem Sie die Änderungen im PR diskutieren und überprüfen.
6. Sobald die Änderungen überprüft und genehmigt wurden, führen Sie den PR in den Main-Branch zusammen.
7. Nach der Zusammenführung können Sie den Feature-Branch löschen.
