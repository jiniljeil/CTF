import createDOMPurify from "https://cdn.jsdelivr.net/npm/dompurify@3.1.5/+esm";

const DOMPurify = createDOMPurify(window);
const DOMPURIFY_CONFIG = {
  RETURN_DOM_FRAGMENT: true,
  FORCE_BODY: true,
  FORBID_ATTR: ["name", "id"],
  FORBID_TAGS: ["template", "svg", "math", "xmp", "textarea"],
  USE_PROFILES: { html: true },
};
Object.setPrototypeOf(DOMPURIFY_CONFIG, null);

const UNSAFE_CSS_REGEX = /(@import|url[(])/i;

/**
 * @param {string} stylesheetText
 */
function sanitizeStyleSheet(stylesheetText) {
  // Early exit for imports and external URLs
  if (UNSAFE_CSS_REGEX.test(stylesheetText)) {
    return "";
  }
  const sheet = new CSSStyleSheet();
  sheet.replaceSync(stylesheetText);
  for (let i = sheet.cssRules.length - 1; i >= 0; i--) {
    const rule = sheet.cssRules[i];
    if (shouldDeleteRule(rule)) {
      sheet.deleteRule(i);
    }
  }
  const safeCss = Array.from(sheet.cssRules)
    .map((r) => r.cssText)
    .join("\n");

  // Do the check again if somehow @import or url() reappears during re-serialization.
  if (UNSAFE_CSS_REGEX.test(safeCss)) {
    return "";
  }
  return safeCss;
}

/**
 * @param {CSSRule} rule
 * @returns {boolean}
 */
function shouldDeleteRule(rule) {
  if (
    rule instanceof CSSImportRule ||
    rule instanceof CSSMediaRule ||
    rule instanceof CSSFontFaceRule ||
    rule instanceof CSSLayerBlockRule ||
    rule instanceof CSSLayerStatementRule ||
    rule instanceof CSSNamespaceRule ||
    rule instanceof CSSSupportsRule ||
    rule instanceof CSSPageRule ||
    rule instanceof CSSPropertyRule
  ) {
    return true;
  }
  // :has, :before etc. are potentially dangerous.
  if (rule instanceof CSSStyleRule && rule.selectorText.includes(":")) {
    return true;
  }
  return false;
}

DOMPurify.addHook("uponSanitizeElement", (node, data) => {
  if (data.tagName === "style") {
    node.textContent = sanitizeStyleSheet(node.textContent);
  }
});

/**
 * @param {string} html
 * @returns {DocumentFragment}
 */
function sanitize(html) {
  return DOMPurify.sanitize(html, DOMPURIFY_CONFIG);
}

class UntrustedContentElement extends HTMLElement {
  static get observedAttributes() {
    return ["html"];
  }

  constructor() {
    super();
    this._shadow = this.attachShadow({ mode: "closed" });
  }

  get html() {
    return this.getAttribute("html") ?? "";
  }

  set html(val) {
    this.setAttribute("html", val);
  }

  attributeChangedCallback(name, oldValue, newValue) {
    if (name === "html") {
      this._shadow.replaceChildren(sanitize(newValue));
    }
  }
}

customElements.define("untrusted-content", UntrustedContentElement);
